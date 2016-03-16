object scratch {
  def error(msg: String) = throw new Error(msg)
}

scratch.error("poop")


