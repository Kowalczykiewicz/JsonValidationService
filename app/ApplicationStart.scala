import models.DB.db
import models.tables.SchemasTable.query
import slick.dbio.DBIO
import slick.jdbc.H2Profile.api._

import javax.inject._
import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class ApplicationStart {
  Await.ready(db.run(DBIO.seq(query.schema.create)), Duration.Inf)
}
