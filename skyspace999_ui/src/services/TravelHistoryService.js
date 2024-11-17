import http from "../http-common"; 

class TravelHistoryService {
  getAllTravelHistorys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/travelHistory/travelHistorys`, searchDTO);
  }

  get(travelHistoryId) {
    return this.getRequest(`/travelHistory/${travelHistoryId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/travelHistory?field=${matchData}`, null);
  }

  addTravelHistory(data) {
    return http.post("/travelHistory/addTravelHistory", data);
  }

  update(data) {
  	return http.post("/travelHistory/updateTravelHistory", data);
  }
  
  uploadImage(data,travelHistoryId) {
  	return http.postForm("/travelHistory/uploadImage/"+travelHistoryId, data);
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

export default new TravelHistoryService();
