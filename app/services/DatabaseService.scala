package services

import javax.inject.Singleton
import play.api.Logger

@Singleton
class DatabaseService {

  private val logger: Logger = Logger(this.getClass)

  def init(): Unit = {
    logger.info("data base init...")
  }
}
