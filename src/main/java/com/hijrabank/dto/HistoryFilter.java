package com.hijrabank.dto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryFilter {
    
	private Date fromDate;
	private Date toDate;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {
			this.fromDate = simpleDateFormat.parse(fromDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		try {
			this.toDate = simpleDateFormat.parse(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
