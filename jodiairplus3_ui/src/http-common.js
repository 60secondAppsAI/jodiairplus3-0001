import axios from "axios"; 

export default axios.create({
//  baseURL: "https://${application.domain}:8080/",  SET TO THIS baseURL when deploying to VPS
  baseURL: "http://localhost:8090/",
  headers: {
    "Content-type": "application/json",
  },
});


