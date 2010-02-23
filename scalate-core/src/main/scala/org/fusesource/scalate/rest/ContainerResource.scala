package org.fusesource.scalate.rest

import com.sun.jersey.api.NotFoundException
import javax.ws.rs.{POST, Path, PathParam}

/**
 * @version $Revision: 1.1 $
 */
abstract class ContainerResource[K,E,R] {
  def container: Container[K,E]

  @Path("id/{id}")
  def get(@PathParam("id") key : K) : R = {
    println("Loading id '" + key + "'")

    container.get(key) match {
      case Some(e) => createChild(e)
      case _ => throw new NotFoundException("Element " + key + " not found")
    }
  }

  @POST
  def post(element : E) = {
    // TODO validate the new element
    container.put(element)
  }

  def createChild(e: E) : R
}