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
            <delay-table
            v-if="delays && delays.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:delays="delays"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-delays="getAllDelays"
             >

            </delay-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DelayTable from "@/components/DelayTable";
import DelayService from "../services/DelayService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DelayTable,
  },
  data() {
    return {
      delays: [],
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
    async getAllDelays(sortBy='delayId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DelayService.getAllDelays(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.delays.length) {
					this.delays = response.data.delays;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching delays:", error);
        }
        
      } catch (error) {
        console.error("Error fetching delay details:", error);
      }
    },
  },
  mounted() {
    this.getAllDelays();
  },
  created() {
    this.$root.$on('searchQueryForDelaysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDelays();
    })
  }
};
</script>
<style></style>
