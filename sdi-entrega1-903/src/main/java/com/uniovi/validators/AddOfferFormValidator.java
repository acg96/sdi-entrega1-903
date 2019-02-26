package com.uniovi.validators;

import com.uniovi.entities.Offer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class AddOfferFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Offer.class.equals(aClass);
	}
	
	private boolean checkDate(String date) {
		try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "details", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");
		if (offer.getTitle().length() < 3 || offer.getTitle().length() > 15) {
			errors.rejectValue("title", "Error.addOffer.title.length");
		}
		if (offer.getDetails().length() < 3 || offer.getDetails().length() > 50) {
			errors.rejectValue("details", "Error.addOffer.details.length");
		}
		if (!checkDate(offer.getDate())) {
			errors.rejectValue("date", "Error.addOffer.date");
		}
		if (offer.getPrice() <= 0) {
			errors.rejectValue("price", "Error.addOffer.price");
		}
	}
}
