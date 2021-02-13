package zio.interop.test

import cats.Monad
import zio.UIO
import zio.interop.catz.core._

object CoreSummonSpec {
  val _ = implicitly[Monad[UIO]]
}