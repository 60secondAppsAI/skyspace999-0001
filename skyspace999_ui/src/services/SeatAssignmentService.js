import http from "../http-common"; 

class SeatAssignmentService {
  getAllSeatAssignments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/seatAssignment/seatAssignments`, searchDTO);
  }

  get(seatAssignmentId) {
    return this.getRequest(`/seatAssignment/${seatAssignmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/seatAssignment?field=${matchData}`, null);
  }

  addSeatAssignment(data) {
    return http.post("/seatAssignment/addSeatAssignment", data);
  }

  update(data) {
  	return http.post("/seatAssignment/updateSeatAssignment", data);
  }
  
  uploadImage(data,seatAssignmentId) {
  	return http.postForm("/seatAssignment/uploadImage/"+seatAssignmentId, data);
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

export default new SeatAssignmentService();
