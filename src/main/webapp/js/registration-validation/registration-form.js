const registrationForm = document.getElementById('registration-form');

function initRegistrationFormHandlers(registrationForm) {
  const nameInput = registrationForm.querySelector('#name');
  const surnameInput = registrationForm.querySelector('#surname');
  const loginInput = registrationForm.querySelector('#login');
  const emailInput = registrationForm.querySelector('#email');
  const acceptTermsCheckbox = registrationForm.querySelector('#terms');
  const passwordInput = registrationForm.querySelector('#password');
  const repasswordInput = registrationForm.querySelector('#repassword');

  nameInput.addEventListener('input', handleName);
  surnameInput.addEventListener('input', handleSurname);
  loginInput.addEventListener('input', handleLogin);
  emailInput.addEventListener('input', handleEmail);
  passwordInput.addEventListener('input', handlePassword);
  repasswordInput.addEventListener('input', (event)=>handleRePassword(event, passwordInput));
  acceptTermsCheckbox.addEventListener('change', handleAcceptTerms);
  registrationForm.addEventListener('submit', handleRegistrationFormSubmit);
};

initRegistrationFormHandlers(registrationForm);