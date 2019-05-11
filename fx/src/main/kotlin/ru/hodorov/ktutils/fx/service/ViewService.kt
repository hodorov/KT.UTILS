package ru.hodorov.ktutils.fx.service

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Modality
import javafx.stage.Stage
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import ru.hodorov.ktutils.fx.controller.BaseController
import ru.hodorov.ktutils.fx.controller.LoadPopupController
import kotlin.random.Random

private val log = KotlinLogging.logger { }

@Service
class ViewService {

    @Autowired
    private lateinit var context: ApplicationContext

    fun getMainView(stage: Stage): Parent {
        return loadView<BaseController>("fxml/main.fxml", stage).first
    }

    fun getLoadPopup(title: String, text: String, secondText: String, parentStage: Stage?): LoadPopupController {
        val stage = Stage()
        stage.title = title
        stage.isResizable = false
        parentStage?.let {
            stage.initModality(Modality.APPLICATION_MODAL)
            stage.initOwner(parentStage)
        }
        val view = loadView<LoadPopupController>("fxml/loadPopup.fxml", stage)
        stage.scene = Scene(view.first)
        view.second.updateText(text)
        view.second.updateSecondText(secondText)
        stage.centerOnScreen()
        stage.show()
        return view.second
    }

    internal fun <T : BaseController> loadView(url: String, stage: Stage): Pair<Parent, T> {
        javaClass.classLoader.getResourceAsStream(url).use {
            val loader = FXMLLoader()
            loader.load<Parent?>(it)
            val controller = loader.getController<T>()
            controller.stage = stage
            val beanName = "${controller.javaClass}-${Random.nextInt(1, 9999)}"
            log.debug { "Registry controller as bean with name \"$beanName\"" }
            context.autowireCapableBeanFactory.autowireBean(controller)
            context.autowireCapableBeanFactory.initializeBean(controller, beanName)
            return Pair(loader.getRoot<Parent>(), controller)
        }
    }
}