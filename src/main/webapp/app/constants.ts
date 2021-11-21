// START added by JulioJu
import { helpers } from 'vuelidate/lib/validators';

// RegEx from https://www.thepolyglotdeveloper.com/2015/05/use-regex-to-test-password-strength-in-javascript/
export const PASSWORD_PATTERN = helpers.regex('alpha', /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/);
// END added by JulioJu

// Errors
export const PROBLEM_BASE_URL = 'https://www.jhipster.tech/problem';
export const EMAIL_ALREADY_USED_TYPE = `${PROBLEM_BASE_URL}/email-already-used`;
export const LOGIN_ALREADY_USED_TYPE = `${PROBLEM_BASE_URL}/login-already-used`;
