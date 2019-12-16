export default function loggedIn() {
  return localStorage.getItem('username') !== null;
}