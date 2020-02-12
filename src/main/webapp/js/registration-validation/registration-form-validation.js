function addValidationClasses(field, isValid) {
    field.classList.toggle('is-valid', isValid);
    field.classList.toggle('is-invalid', !isValid);
}

const LOGIN_REG_EXP = /^[a-zA-Z]([a-zA-Z0-9\.](?<!\.\.)){2,30}[a-zA-Z0-9]$/;

function validateLogin(userNameInput) {
    addValidationClasses(userNameInput, stringMatchRegExp(LOGIN_REG_EXP, userNameInput.value));
}

const stringMatchRegExp = (regExp, value) => regExp.test(value);
const EMAIL_REG_EXP = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

function validateEmail(userEmailInput) {
    addValidationClasses(userEmailInput, stringMatchRegExp(EMAIL_REG_EXP, userEmailInput.value));
}

const minPasswordLength = 8;

function validatePassword(userPasswordInput) {
    addValidationClasses(userPasswordInput, passwordMatch(userPasswordInput.value));
}

function validateRePassword(userRePasswordInput, userPasswordInput) {
    addValidationClasses(userRePasswordInput, userRePasswordInput.value == userPasswordInput.value);
}

function passwordMatch(password) {
    if (password.length < minPasswordLength) {
        return false;
    }
    return containsLowercase(password)
        && containsUppercase(password)
        && containsNumber(password);
}

const containsLowercase = (text) => /[a-z]/.test(text)
const containsUppercase = (text) => /[A-Z]/.test(text)
const containsNumber = (text) => /[0-9]/.test(text)

const NAME_REG_EXP = /^[\p{L}\p{M} ]+$/u;

function validateName(nameInput) {
    addValidationClasses(nameInput, stringMatchRegExp(NAME_REG_EXP, nameInput.value));
}
function validateSurname(surnameInput) {
    addValidationClasses(surnameInput, stringMatchRegExp(NAME_REG_EXP, surnameInput.value));
}

function validateAcceptTerms(acceptTermsCheckbox) {
    addValidationClasses(acceptTermsCheckbox, acceptTermsCheckbox.checked);
}

function validateAll(registrationForm) {
    const nameInput = registrationForm.querySelector('#name');
    const surnameInput = registrationForm.querySelector('#surname');
    const loginInput = registrationForm.querySelector('#login');
    const emailInput = registrationForm.querySelector('#email');
    const acceptTermsCheckbox = registrationForm.querySelector('#terms');
    const passwordInput = registrationForm.querySelector('#password');
    const repasswordInput = registrationForm.querySelector('#repassword');
  
    validateName(nameInput);
    validateSurname(surnameInput);
    validateLogin(loginInput);
    validateEmail(emailInput);
    validatePassword(passwordInput);
    validateRePassword(repasswordInput, passwordInput);
    validateAcceptTerms(acceptTermsCheckbox);
    let allValid = true;
    for (let i = 0; i < registrationForm.elements.length; i++) {
        allValid &= !registrationForm.elements[i].classList.contains('is-invalid');
    }
    return allValid;
}