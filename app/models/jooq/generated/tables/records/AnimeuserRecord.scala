/*
 * This file is generated by jOOQ.
 */
package generated.tables.records


import generated.tables.Animeuser

import java.lang.Float
import java.lang.Integer
import java.lang.Long
import java.lang.String

import javax.annotation.Generated

import org.jooq.Field
import org.jooq.Record2
import org.jooq.Record4
import org.jooq.Row4
import org.jooq.impl.UpdatableRecordImpl

import scala.Array


/**
 * This class is generated by jOOQ.
 */
@Generated(
  value = Array(
    "http://www.jooq.org",
    "jOOQ version:3.11.0"
  ),
  comments = "This class is generated by jOOQ"
)
class AnimeuserRecord extends UpdatableRecordImpl[AnimeuserRecord](Animeuser.ANIMEUSER) with Record4[String, Long, Integer, Float] {

  /**
   * Setter for <code>public.animeuser.username</code>.
   */
  def setUsername(value : String) : Unit = {
    set(0, value)
  }

  /**
   * Getter for <code>public.animeuser.username</code>.
   */
  def getUsername : String = {
    val r = get(0)
    if (r == null) null else r.asInstanceOf[String]
  }

  /**
   * Setter for <code>public.animeuser.anime</code>.
   */
  def setAnime(value : Long) : Unit = {
    set(1, value)
  }

  /**
   * Getter for <code>public.animeuser.anime</code>.
   */
  def getAnime : Long = {
    val r = get(1)
    if (r == null) null else r.asInstanceOf[Long]
  }

  /**
   * Setter for <code>public.animeuser.watchedepisodes</code>.
   */
  def setWatchedepisodes(value : Integer) : Unit = {
    set(2, value)
  }

  /**
   * Getter for <code>public.animeuser.watchedepisodes</code>.
   */
  def getWatchedepisodes : Integer = {
    val r = get(2)
    if (r == null) null else r.asInstanceOf[Integer]
  }

  /**
   * Setter for <code>public.animeuser.score</code>.
   */
  def setScore(value : Float) : Unit = {
    set(3, value)
  }

  /**
   * Getter for <code>public.animeuser.score</code>.
   */
  def getScore : Float = {
    val r = get(3)
    if (r == null) null else r.asInstanceOf[Float]
  }

  // -------------------------------------------------------------------------
  // Primary key information
  // -------------------------------------------------------------------------
  override def key : Record2[String, Long] = {
    return super.key.asInstanceOf[ Record2[String, Long] ]
  }

  // -------------------------------------------------------------------------
  // Record4 type implementation
  // -------------------------------------------------------------------------

  override def fieldsRow : Row4[String, Long, Integer, Float] = {
    super.fieldsRow.asInstanceOf[ Row4[String, Long, Integer, Float] ]
  }

  override def valuesRow : Row4[String, Long, Integer, Float] = {
    super.valuesRow.asInstanceOf[ Row4[String, Long, Integer, Float] ]
  }
  override def field1 : Field[String] = Animeuser.ANIMEUSER.USERNAME
  override def field2 : Field[Long] = Animeuser.ANIMEUSER.ANIME
  override def field3 : Field[Integer] = Animeuser.ANIMEUSER.WATCHEDEPISODES
  override def field4 : Field[Float] = Animeuser.ANIMEUSER.SCORE
  override def component1 : String = getUsername
  override def component2 : Long = getAnime
  override def component3 : Integer = getWatchedepisodes
  override def component4 : Float = getScore
  override def value1 : String = getUsername
  override def value2 : Long = getAnime
  override def value3 : Integer = getWatchedepisodes
  override def value4 : Float = getScore

  override def value1(value : String) : AnimeuserRecord = {
    setUsername(value)
    this
  }

  override def value2(value : Long) : AnimeuserRecord = {
    setAnime(value)
    this
  }

  override def value3(value : Integer) : AnimeuserRecord = {
    setWatchedepisodes(value)
    this
  }

  override def value4(value : Float) : AnimeuserRecord = {
    setScore(value)
    this
  }

  override def values(value1 : String, value2 : Long, value3 : Integer, value4 : Float) : AnimeuserRecord = {
    this.value1(value1)
    this.value2(value2)
    this.value3(value3)
    this.value4(value4)
    this
  }

  /**
   * Create a detached, initialised AnimeuserRecord
   */
  def this(username : String, anime : Long, watchedepisodes : Integer, score : Float) = {
    this()

    set(0, username)
    set(1, anime)
    set(2, watchedepisodes)
    set(3, score)
  }
}
