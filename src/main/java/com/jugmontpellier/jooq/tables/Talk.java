/**
 * This class is generated by jOOQ
 */
package com.jugmontpellier.jooq.tables;


import com.jugmontpellier.jooq.Keys;
import com.jugmontpellier.jooq.Public;
import com.jugmontpellier.jooq.tables.records.TalkRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.1"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Talk extends TableImpl<TalkRecord> {

	private static final long serialVersionUID = -132383502;

	/**
	 * The reference instance of <code>public.TALK</code>
	 */
	public static final Talk TALK = new Talk();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TalkRecord> getRecordType() {
		return TalkRecord.class;
	}

	/**
	 * The column <code>public.TALK.id_talk</code>.
	 */
	public final TableField<TalkRecord, UUID> ID_TALK = createField("id_talk", org.jooq.impl.SQLDataType.UUID.nullable(false).defaulted(true), this, "");

	/**
	 * The column <code>public.TALK.title</code>.
	 */
	public final TableField<TalkRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>public.TALK.description</code>.
	 */
	public final TableField<TalkRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

	/**
	 * The column <code>public.TALK.date_</code>.
	 */
	public final TableField<TalkRecord, Timestamp> DATE_ = createField("date_", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

	/**
	 * The column <code>public.TALK.speaker_fk</code>.
	 */
	public final TableField<TalkRecord, UUID> SPEAKER_FK = createField("speaker_fk", org.jooq.impl.SQLDataType.UUID, this, "");

	/**
	 * Create a <code>public.TALK</code> table reference
	 */
	public Talk() {
		this("TALK", null);
	}

	/**
	 * Create an aliased <code>public.TALK</code> table reference
	 */
	public Talk(String alias) {
		this(alias, TALK);
	}

	private Talk(String alias, Table<TalkRecord> aliased) {
		this(alias, aliased, null);
	}

	private Talk(String alias, Table<TalkRecord> aliased, Field<?>[] parameters) {
		super(alias, Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<TalkRecord> getPrimaryKey() {
		return Keys.TALK_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<TalkRecord>> getKeys() {
		return Arrays.<UniqueKey<TalkRecord>>asList(Keys.TALK_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ForeignKey<TalkRecord, ?>> getReferences() {
		return Arrays.<ForeignKey<TalkRecord, ?>>asList(Keys.TALK__TALK_SPEAKER_FK_FKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Talk as(String alias) {
		return new Talk(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Talk rename(String name) {
		return new Talk(name, null);
	}
}