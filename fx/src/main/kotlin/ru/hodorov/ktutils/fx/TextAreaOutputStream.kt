package ru.hodorov.ktutils.fx

import javafx.scene.control.TextArea
import java.io.OutputStream

class TextAreaOutputStream(private val textArea: TextArea) : OutputStream() {
    override fun write(b: Int) {
        textArea.appendText(b.toChar().toString())
    }
}