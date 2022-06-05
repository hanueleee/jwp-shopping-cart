package woowacourse.shoppingcart.domain.vo;

import woowacourse.shoppingcart.exception.EmailValidationException;
import woowacourse.utils.StringValidator;

public class Email {

    private static final int EMAIL_MIN_LENGTH = 8;
    private static final int EMAIL_MAX_LENGTH = 50;
    private static final String EMAIL_REGEX
            = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

    private final String value;

    private Email(final String value) {
        this.value = value;
    }

    public static Email valueOf(final String value) {
        validateEmail(value);
        return new Email(value);
    }

    public static Email empty() {
        return new Email(null);
    }

    private static void validateEmail(final String email) {
        StringValidator.validateNullOrHasSpace(email, new EmailValidationException("이메일에는 공백이 들어가면 안됩니다."));
        StringValidator.validateLength(
                EMAIL_MIN_LENGTH,
                EMAIL_MAX_LENGTH,
                email,
                new EmailValidationException("이메일은 8자 이상 50자 이하여야합니다.")
        );
        StringValidator.validateRegex(email, EMAIL_REGEX, new EmailValidationException("이메일 형식을 지켜야합니다."));
    }

    public String getValue() {
        return value;
    }
}