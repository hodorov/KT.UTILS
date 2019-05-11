package ru.hodorov.ktutils.fx.controller

import javafx.fxml.FXML
import javafx.scene.control.ProgressBar
import javafx.scene.text.Text
import javafx.stage.Stage

class LoadPopupController: BaseController() {
    @FXML
    private lateinit var text: Text

    @FXML
    private lateinit var secondText: Text

    @FXML
    private lateinit var bar: ProgressBar

    public fun updateText(newText: String) {
        text.text = newText
    }

    public fun updateSecondText(newText: String) {
        secondText.text = newText
    }

    public fun updateProgress(newValue: Double) {
        bar.progress = newValue
    }

    public fun close() {
        (text.scene.window as Stage).close()
    }
}