/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.uuid;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Base58UuidGenerator  implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor session, Object object)throws HibernateException {
		return base58Uuid(UUID.randomUUID());
	}

	private static String base58Uuid(UUID uuid) {
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());
		return Base58.encode(bb.array());
	}
}
