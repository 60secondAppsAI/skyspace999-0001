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
            <seatAssignment-table
            v-if="seatAssignments && seatAssignments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:seatAssignments="seatAssignments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-seat-assignments="getAllSeatAssignments"
             >

            </seatAssignment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SeatAssignmentTable from "@/components/SeatAssignmentTable";
import SeatAssignmentService from "../services/SeatAssignmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SeatAssignmentTable,
  },
  data() {
    return {
      seatAssignments: [],
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
    async getAllSeatAssignments(sortBy='seatAssignmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SeatAssignmentService.getAllSeatAssignments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.seatAssignments.length) {
					this.seatAssignments = response.data.seatAssignments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching seatAssignments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching seatAssignment details:", error);
      }
    },
  },
  mounted() {
    this.getAllSeatAssignments();
  },
  created() {
    this.$root.$on('searchQueryForSeatAssignmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSeatAssignments();
    })
  }
};
</script>
<style></style>
