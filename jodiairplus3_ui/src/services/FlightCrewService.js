import http from "../http-common";

class FlightCrewService {
  getAllFlightCrews(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/flightCrew/flightCrews`, searchDTO);
  }

  get(flightCrewId) {
    return this.getRequest(`/flightCrew/${flightCrewId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/flightCrew?field=${matchData}`, null);
  }

  addFlightCrew(data) {
    return http.post("/flightCrew/addFlightCrew", data);
  }

  update(data) {
  	return http.post("/flightCrew/updateFlightCrew", data);
  }
  
  uploadImage(data,flightCrewId) {
  	return http.postForm("/flightCrew/uploadImage/"+flightCrewId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new FlightCrewService();
