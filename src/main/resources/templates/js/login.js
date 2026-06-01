function handleLogin() {
  const username = document.getElementById('username').value.trim();
  const password = document.getElementById('password').value.trim();

  if (!username || !password) {
    alert('Please enter your username/ID and password.');
    return;
  }

  window.location.href = 'student.html';
}

document.getElementById('password').addEventListener('keydown', function (e) {
  if (e.key === 'Enter') handleLogin();
});

document.getElementById('username').addEventListener('keydown', function (e) {
  if (e.key === 'Enter') document.getElementById('password').focus();
});
