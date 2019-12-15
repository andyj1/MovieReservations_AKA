export default function loggedIn() {
  return localStorage.getItem('user_id') !== null;
}