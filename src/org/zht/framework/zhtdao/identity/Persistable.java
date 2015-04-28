/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.zhtdao.identity;

import java.io.Serializable;
public abstract interface Persistable extends Serializable {
	  public abstract Serializable getId();
	  public abstract boolean isNew();
}
