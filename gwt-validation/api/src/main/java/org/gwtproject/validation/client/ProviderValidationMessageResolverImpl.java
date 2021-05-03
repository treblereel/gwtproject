package org.gwtproject.validation.client;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 8/30/19
 */
public class ProviderValidationMessageResolverImpl implements UserValidationMessagesResolver {

    Map<String, String> holder = new HashMap<>();

    public ProviderValidationMessageResolverImpl() {
        holder.put("javax.validation.constraints.AssertFalse.message", "must be false");
        holder.put("javax.validation.constraints.AssertTrue.message", " must be true");
        holder.put("javax.validation.constraints.DecimalMax.message", " must be less than ${inclusive == true ? 'or equal to ' : ''}{value}");
        holder.put("javax.validation.constraints.DecimalMin.message", " must be greater than ${inclusive == true ? 'or equal to ' : ''}{value}");
        holder.put("javax.validation.constraints.Digits.message", " numeric value out of bounds (<{integer} digits>.<{fraction} digits> expected)");
        holder.put("javax.validation.constraints.Email.message", " must be a well-formed email address");
        holder.put("javax.validation.constraints.Future.message", " must be a future date");
        holder.put("javax.validation.constraints.FutureOrPresent.message", " must be a date in the present or in the future");
        holder.put("javax.validation.constraints.Max.message", " must be less than or equal to {value}");
        holder.put("javax.validation.constraints.Min.message", " must be greater than or equal to {value}");
        holder.put("javax.validation.constraints.Negative.message", " must be less than 0");
        holder.put("javax.validation.constraints.NegativeOrZero.message", " must be less than or equal to 0");
        holder.put("javax.validation.constraints.NotBlank.message", " must not be blank");
        holder.put("javax.validation.constraints.NotEmpty.message", " must not be empty");
        holder.put("javax.validation.constraints.NotNull.message", " must not be null");
        holder.put("javax.validation.constraints.Null.message", " must be null");
        holder.put("javax.validation.constraints.Past.message", " must be a past date");
        holder.put("javax.validation.constraints.PastOrPresent.message", " must be a date in the past or in the present");
        holder.put("javax.validation.constraints.Pattern.message", " must match \"{regexp}\"");
        holder.put("javax.validation.constraints.Positive.message", " must be greater than 0");
        holder.put("javax.validation.constraints.PositiveOrZero.message", " must be greater than or equal to 0");
        holder.put("javax.validation.constraints.Size.message", " size must be between {min} and {max}");
    }

    @Override
    public String get(String key) {
        return holder.get(key);
    }
}
