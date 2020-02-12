function handleName(event) {
    validateName(event.target);
}

function handleSurname(event) {
    validateSurname(event.target);
}

function handleLogin(event) {
    validateLogin(event.target);
}

function handleEmail(event) {
    validateEmail(event.target);
}

function handlePassword(event) {
    validatePassword(event.target);
}

function handleRePassword(event,passwordInput) {
    validateRePassword(event.target, passwordInput);
}

function handleAcceptTerms(event) {
    validateAcceptTerms(event.target);
}

function handleRegistrationFormSubmit(event) {
    if (!validateAll(event.target)) {
        event.preventDefault();
    }
}