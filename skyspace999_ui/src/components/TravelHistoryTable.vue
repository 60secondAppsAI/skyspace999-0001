
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Travel Historys</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalTravelHistorys = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalTravelHistorys">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add TravelHistory</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="TravelHistoryId" type="text" placeholder="Enter TravelHistoryId" v-model="travelHistoryToAdd.travelHistoryId"></base-input>
  <base-input label="TravelDate" type="text" placeholder="Enter TravelDate" v-model="travelHistoryToAdd.travelDate"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="travelHistorys" :row-key="record => record.TravelHistoryId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <TravelHistoryPictureView :travelHistorys="travelHistorys" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import TravelHistoryService from "../services/TravelHistoryService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import TravelHistoryPictureView from './TravelHistoryPictureView.vue';


const travelHistorysColumns = [
  "travelHistoryId",
  "year",
  "date",
  "competitionId",
  "travelHistoryId"
]

export default {
  props: {
    travelHistorys: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    TravelHistoryPictureView
  },

  data() {
    return {
      modalTravelHistorys: false,
        isTableView: true,

      columns: [
        {
          title: 'Travel History Id',
		dataIndex: 'travelHistoryId',
          visible: true,
          scopedSlots: { customRender: 'travelHistoryId' },
          sorter: true
          //sorter: (a, b) => a.travelHistoryId - b.travelHistoryId,
          //sorter: (a, b) => a.travelHistoryId.localeCompare(b.travelHistoryId),
        },
        {
          title: 'Travel Date',
		dataIndex: 'travelDate',
          visible: true,
          scopedSlots: { customRender: 'travelDate' },
          sorter: true
          //sorter: (a, b) => a.travelDate - b.travelDate,
          //sorter: (a, b) => a.travelDate.localeCompare(b.travelDate),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} travelHistorys`,
      },

      travelHistorys: [],
      travelHistoryToAdd: {},

      travelHistorysTable: {
        columns: [...travelHistorysColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'travelHistoryId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderTravelHistorysTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let travelHistorysTableData = [];
      for (let i = 0; i < this.travelHistorys.length; i++) {
        travelHistorysTableData.push({
          id: i,
          travelHistoryId: this.travelHistorys[i].travelHistoryId,
          year: this.travelHistorys[i].year,
          date: this.travelHistorys[i].date,
          competitionId: this.travelHistorys[i].competitionId,
          travelHistoryId: this.travelHistorys[i].travelHistoryId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-travel-historys',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToAirportDetail(id) {
      this.$router.push({ name: 'AirportDetail', params: { airportId: id.toString() }})
    },
    routingToAirplaneDetail(id) {
      this.$router.push({ name: 'AirplaneDetail', params: { airplaneId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToTicketDetail(id) {
      this.$router.push({ name: 'TicketDetail', params: { ticketId: id.toString() }})
    },
    routingToCrewMemberDetail(id) {
      this.$router.push({ name: 'CrewMemberDetail', params: { crewMemberId: id.toString() }})
    },
    routingToFlightCrewDetail(id) {
      this.$router.push({ name: 'FlightCrewDetail', params: { flightCrewId: id.toString() }})
    },
    routingToBaggageDetail(id) {
      this.$router.push({ name: 'BaggageDetail', params: { baggageId: id.toString() }})
    },
    routingToFrequentFlyerDetail(id) {
      this.$router.push({ name: 'FrequentFlyerDetail', params: { frequentFlyerId: id.toString() }})
    },
    routingToCheckInDetail(id) {
      this.$router.push({ name: 'CheckInDetail', params: { checkInId: id.toString() }})
    },
    routingToGateDetail(id) {
      this.$router.push({ name: 'GateDetail', params: { gateId: id.toString() }})
    },
    routingToLoungeAccessDetail(id) {
      this.$router.push({ name: 'LoungeAccessDetail', params: { loungeAccessId: id.toString() }})
    },
    routingToMealDetail(id) {
      this.$router.push({ name: 'MealDetail', params: { mealId: id.toString() }})
    },
    routingToSeatAssignmentDetail(id) {
      this.$router.push({ name: 'SeatAssignmentDetail', params: { seatAssignmentId: id.toString() }})
    },
    routingToTravelHistoryDetail(id) {
      this.$router.push({ name: 'TravelHistoryDetail', params: { travelHistoryId: id.toString() }})
    },
    routingToConnectionFlightDetail(id) {
      this.$router.push({ name: 'ConnectionFlightDetail', params: { connectionFlightId: id.toString() }})
    },
    routingToDelayDetail(id) {
      this.$router.push({ name: 'DelayDetail', params: { delayId: id.toString() }})
    },
    routingToCancellationDetail(id) {
      this.$router.push({ name: 'CancellationDetail', params: { cancellationId: id.toString() }})
    },
    routingToRefundDetail(id) {
      this.$router.push({ name: 'RefundDetail', params: { refundId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForTravelHistorysChanged', this.searchQuery);
		//this.renderTravelHistorysTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalTravelHistorys = false;

      const currentDate = new Date().getTime();
      this.travelHistoryToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.travelHistoryToAdd);
      console.log(jsonData);
      
      const res = await TravelHistoryService.addTravelHistory(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderTravelHistorysTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
