package module

import com.google.inject.AbstractModule
import services.AppStart

class EagerLoaderModule extends AbstractModule {

  override def configure() = {
    bind(classOf[AppStart]).asEagerSingleton
  }

}
