/**
 * This class is generated by jOOQ
 */
package com.jugmontpellier.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Generated;


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
public class Talk implements Serializable {

	private static final long serialVersionUID = 1525317400;

	private UUID      idTalk;
	private String    title;
	private String    description;
	private Timestamp date_;
	private UUID      speakerFk;

	public Talk() {}

	public Talk(Talk value) {
		this.idTalk = value.idTalk;
		this.title = value.title;
		this.description = value.description;
		this.date_ = value.date_;
		this.speakerFk = value.speakerFk;
	}

	public Talk(
		UUID      idTalk,
		String    title,
		String    description,
		Timestamp date_,
		UUID      speakerFk
	) {
		this.idTalk = idTalk;
		this.title = title;
		this.description = description;
		this.date_ = date_;
		this.speakerFk = speakerFk;
	}

	public UUID getIdTalk() {
		return this.idTalk;
	}

	public void setIdTalk(UUID idTalk) {
		this.idTalk = idTalk;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDate_() {
		return this.date_;
	}

	public void setDate_(Timestamp date_) {
		this.date_ = date_;
	}

	public UUID getSpeakerFk() {
		return this.speakerFk;
	}

	public void setSpeakerFk(UUID speakerFk) {
		this.speakerFk = speakerFk;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Talk (");

		sb.append(idTalk);
		sb.append(", ").append(title);
		sb.append(", ").append(description);
		sb.append(", ").append(date_);
		sb.append(", ").append(speakerFk);

		sb.append(")");
		return sb.toString();
	}
}