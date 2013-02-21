package org.agilegrenoble.objectcalisthenics

import scala.reflect.{BeanProperty, BooleanBeanProperty}
//remove if not needed
import scala.collection.JavaConversions._

class Item(@BeanProperty var name: String, @BeanProperty var sellIn: Int, @BeanProperty var quality: Int)
   
