package com.sqtec.takecontrol.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Spending implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3646287552155976826L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private long id;

	@Column(name = "day", nullable = false)
	private long day;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "recurrent", nullable = false)
	private boolean recurrent;
	
	@Column(name = "start_date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
	private Date endDate;

	public Spending() {
		super();
	}

	public Spending(long id, long day, Category category, String description, BigDecimal amount, boolean recurrent,
			Date startDate, Date endDate) {
		super();
		this.id = id;
		this.day = day;
		this.category = category;
		this.description = description;
		this.amount = amount;
		this.recurrent = recurrent;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean isRecurrent() {
		return recurrent;
	}

	public void setRecurrent(boolean recurrent) {
		this.recurrent = recurrent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Spending [id=" + id + ", day=" + day + ", description=" + description + ", amount=" + amount
				+ ", recurrent=" + recurrent + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
