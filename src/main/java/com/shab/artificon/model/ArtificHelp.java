/**
 * 
 */
package com.shab.artificon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ArtificHelp {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@Column(length = 3000)
	private String steps;

	public ArtificHelp() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param steps
	 */
	public ArtificHelp(Integer id, String name, String steps) {
		super();
		this.id = id;
		this.name = name;
		this.steps = steps;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the steps
	 */
	public String getSteps() {
		return steps;
	}

	/**
	 * @param steps
	 *            the steps to set
	 */
	public void setSteps(String steps) {
		this.steps = steps;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArtificHelp [id=" + id + ", name=" + name + ", steps=" + steps + "]";
	}

}
