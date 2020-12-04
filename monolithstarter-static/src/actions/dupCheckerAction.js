import axios from 'axios';

export async function getRefinedData() {
  return (await axios.get('http://localhost:8080/check/dup')).data;
}
