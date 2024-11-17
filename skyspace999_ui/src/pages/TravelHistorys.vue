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
            <travelHistory-table
            v-if="travelHistorys && travelHistorys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:travelHistorys="travelHistorys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-travel-historys="getAllTravelHistorys"
             >

            </travelHistory-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TravelHistoryTable from "@/components/TravelHistoryTable";
import TravelHistoryService from "../services/TravelHistoryService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TravelHistoryTable,
  },
  data() {
    return {
      travelHistorys: [],
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
    async getAllTravelHistorys(sortBy='travelHistoryId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TravelHistoryService.getAllTravelHistorys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.travelHistorys.length) {
					this.travelHistorys = response.data.travelHistorys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching travelHistorys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching travelHistory details:", error);
      }
    },
  },
  mounted() {
    this.getAllTravelHistorys();
  },
  created() {
    this.$root.$on('searchQueryForTravelHistorysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTravelHistorys();
    })
  }
};
</script>
<style></style>
