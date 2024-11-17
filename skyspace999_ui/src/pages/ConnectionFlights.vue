<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <connectionFlight-table
            v-if="connectionFlights && connectionFlights.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:connectionFlights="connectionFlights"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-connection-flights="getAllConnectionFlights"
             >

            </connectionFlight-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ConnectionFlightTable from "@/components/ConnectionFlightTable";
import ConnectionFlightService from "../services/ConnectionFlightService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ConnectionFlightTable,
  },
  data() {
    return {
      connectionFlights: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllConnectionFlights(sortBy='connectionFlightId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ConnectionFlightService.getAllConnectionFlights(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.connectionFlights.length) {
					this.connectionFlights = response.data.connectionFlights;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching connectionFlights:", error);
        }
        
      } catch (error) {
        console.error("Error fetching connectionFlight details:", error);
      }
    },
  },
  mounted() {
    this.getAllConnectionFlights();
  },
  created() {
    this.$root.$on('searchQueryForConnectionFlightsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllConnectionFlights();
    })
  }
};
</script>
<style></style>
