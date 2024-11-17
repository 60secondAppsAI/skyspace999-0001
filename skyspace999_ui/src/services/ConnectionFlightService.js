import http from "../http-common"; 

class ConnectionFlightService {
  getAllConnectionFlights(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/connectionFlight/connectionFlights`, searchDTO);
  }

  get(connectionFlightId) {
    return this.getRequest(`/connectionFlight/${connectionFlightId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/connectionFlight?field=${matchData}`, null);
  }

  addConnectionFlight(data) {
    return http.post("/connectionFlight/addConnectionFlight", data);
  }

  update(data) {
  	return http.post("/connectionFlight/updateConnectionFlight", data);
  }
  
  uploadImage(data,connectionFlightId) {
  	return http.postForm("/connectionFlight/uploadImage/"+connectionFlightId, data);
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

export default new ConnectionFlightService();
