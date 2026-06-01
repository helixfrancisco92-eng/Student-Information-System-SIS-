function handleRole(role) {
  if (role === 'student') {
    window.location.href = 'student-login.html';
  } else if (role === 'admin') {
    window.location.href = 'admin.html';
  }
}
