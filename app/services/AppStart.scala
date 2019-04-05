package services

import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future

@Singleton
class AppStart @Inject()(dbService: DatabaseService) (appLifecycle: ApplicationLifecycle) {

  private val logger: Logger = Logger(this.getClass)
  logger.info("Application Start")
  dbService.init()

  appLifecycle.addStopHook {() =>
    logger.info("Application stoping")
    Future.successful(())
  }
}
