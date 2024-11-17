import http from "../http-common"; 

class DelayService {
  getAllDelays(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/delay/delays`, searchDTO);
  }

  get(delayId) {
    return this.getRequest(`/delay/${delayId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/delay?field=${matchData}`, null);
  }

  addDelay(data) {
    return http.post("/delay/addDelay", data);
  }

  update(data) {
  	return http.post("/delay/updateDelay", data);
  }
  
  uploadImage(data,delayId) {
  	return http.postForm("/delay/uploadImage/"+delayId, data);
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

export default new DelayService();
