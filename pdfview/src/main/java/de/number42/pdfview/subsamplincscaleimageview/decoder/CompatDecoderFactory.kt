package de.number42.pdfview.subsamplincscaleimageview.decoder

import android.graphics.Bitmap
import java.lang.reflect.InvocationTargetException

/**
 * Compatibility factory to instantiate decoders with empty public constructors.
 * @param <T> The base type of the decoder this factory will produce.
</T> */
class CompatDecoderFactory<T>
/**
 * Construct a factory for the given class. This must have a constructor that accepts a [Bitmap.Config] instance.
 * @param clazz a class that implements [ImageDecoder] or [ImageRegionDecoder].
 * @param bitmapConfig bitmap configuration to be used when loading images.
 */
@JvmOverloads constructor(
  private val clazz: Class<out T>,
  private val bitmapConfig: Bitmap.Config? = null
) : DecoderFactory<T> {

  @Throws(
      IllegalAccessException::class, InstantiationException::class, NoSuchMethodException::class,
      InvocationTargetException::class
  )
  override fun make(): T {
    return if (bitmapConfig == null) {
      clazz.newInstance()
    } else {
      val ctor = clazz.getConstructor(Bitmap.Config::class.java)
      ctor.newInstance(bitmapConfig)
    }
  }

}
/**
 * Construct a factory for the given class. This must have a default constructor.
 * @param clazz a class that implements [ImageDecoder] or [ImageRegionDecoder].
 */
