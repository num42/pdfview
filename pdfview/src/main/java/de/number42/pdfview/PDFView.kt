package de.number42.pdfview

import android.content.Context
import android.util.AttributeSet
import de.number42.pdfview.subsamplincscaleimageview.ImageSource
import de.number42.pdfview.subsamplincscaleimageview.SubsamplingScaleImageView
import de.number42.pdfview.subsamplincscaleimageview.decoder.DecoderFactory
import java.io.File

class PDFView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : SubsamplingScaleImageView(context, attrs) {

    private var mfile: File? = null
    private var mScale: Float = 8f

    init {
        setMinimumTileDpi(120)
        setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_START)
    }

    fun fromAsset(assetFileName: String): PDFView {
        mfile = FileUtils.fileFromAsset(context, assetFileName)
        return this
    }

    fun fromFile(file: File): PDFView {
        mfile = file
        return this
    }

    fun fromFile(filePath: String): PDFView {
        mfile = File(filePath)
        return this
    }

    fun scale(scale: Float): PDFView {
        mScale = scale
        return this
    }

    fun show() {
        val source = ImageSource.uri(mfile!!.path)


        setRegionDecoderFactory ( object: DecoderFactory<PDFRegionDecoder> {
            override fun make(): PDFRegionDecoder {
                return PDFRegionDecoder( this@PDFView, mfile!!, mScale)
            }
        })
        setImage(source)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        this.recycle()
    }
}