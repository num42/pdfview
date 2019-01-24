package de.number42.pdfview

import android.app.Activity
import android.os.Bundle
import de.number42.pdfview.example.R

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    findViewById<PDFView>(R.id.activityMainPdfView).fromAsset("paper.pdf")
        .show()
  }
}
