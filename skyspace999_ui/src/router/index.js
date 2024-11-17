import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Airplanes from  '@/pages/Airplanes.vue';
import AirplaneDetail from  '@/pages/AirplaneDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import CrewMembers from  '@/pages/CrewMembers.vue';
import CrewMemberDetail from  '@/pages/CrewMemberDetail.vue';
import FlightCrews from  '@/pages/FlightCrews.vue';
import FlightCrewDetail from  '@/pages/FlightCrewDetail.vue';
import Baggages from  '@/pages/Baggages.vue';
import BaggageDetail from  '@/pages/BaggageDetail.vue';
import FrequentFlyers from  '@/pages/FrequentFlyers.vue';
import FrequentFlyerDetail from  '@/pages/FrequentFlyerDetail.vue';
import CheckIns from  '@/pages/CheckIns.vue';
import CheckInDetail from  '@/pages/CheckInDetail.vue';
import Gates from  '@/pages/Gates.vue';
import GateDetail from  '@/pages/GateDetail.vue';
import LoungeAccesss from  '@/pages/LoungeAccesss.vue';
import LoungeAccessDetail from  '@/pages/LoungeAccessDetail.vue';
import Meals from  '@/pages/Meals.vue';
import MealDetail from  '@/pages/MealDetail.vue';
import SeatAssignments from  '@/pages/SeatAssignments.vue';
import SeatAssignmentDetail from  '@/pages/SeatAssignmentDetail.vue';
import TravelHistorys from  '@/pages/TravelHistorys.vue';
import TravelHistoryDetail from  '@/pages/TravelHistoryDetail.vue';
import ConnectionFlights from  '@/pages/ConnectionFlights.vue';
import ConnectionFlightDetail from  '@/pages/ConnectionFlightDetail.vue';
import Delays from  '@/pages/Delays.vue';
import DelayDetail from  '@/pages/DelayDetail.vue';
import Cancellations from  '@/pages/Cancellations.vue';
import CancellationDetail from  '@/pages/CancellationDetail.vue';
import Refunds from  '@/pages/Refunds.vue';
import RefundDetail from  '@/pages/RefundDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airplanes',
		name: 'Airplanes',
		layout: DefaultLayout,
		component: Airplanes,
	},
	{
	    path: '/airplane/:airplaneId', 
	    name: 'AirplaneDetail',
		layout: DefaultLayout,
	    component: AirplaneDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crewMembers',
		name: 'CrewMembers',
		layout: DefaultLayout,
		component: CrewMembers,
	},
	{
	    path: '/crewMember/:crewMemberId', 
	    name: 'CrewMemberDetail',
		layout: DefaultLayout,
	    component: CrewMemberDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flightCrews',
		name: 'FlightCrews',
		layout: DefaultLayout,
		component: FlightCrews,
	},
	{
	    path: '/flightCrew/:flightCrewId', 
	    name: 'FlightCrewDetail',
		layout: DefaultLayout,
	    component: FlightCrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggages',
		name: 'Baggages',
		layout: DefaultLayout,
		component: Baggages,
	},
	{
	    path: '/baggage/:baggageId', 
	    name: 'BaggageDetail',
		layout: DefaultLayout,
	    component: BaggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/frequentFlyers',
		name: 'FrequentFlyers',
		layout: DefaultLayout,
		component: FrequentFlyers,
	},
	{
	    path: '/frequentFlyer/:frequentFlyerId', 
	    name: 'FrequentFlyerDetail',
		layout: DefaultLayout,
	    component: FrequentFlyerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/checkIns',
		name: 'CheckIns',
		layout: DefaultLayout,
		component: CheckIns,
	},
	{
	    path: '/checkIn/:checkInId', 
	    name: 'CheckInDetail',
		layout: DefaultLayout,
	    component: CheckInDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/gates',
		name: 'Gates',
		layout: DefaultLayout,
		component: Gates,
	},
	{
	    path: '/gate/:gateId', 
	    name: 'GateDetail',
		layout: DefaultLayout,
	    component: GateDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loungeAccesss',
		name: 'LoungeAccesss',
		layout: DefaultLayout,
		component: LoungeAccesss,
	},
	{
	    path: '/loungeAccess/:loungeAccessId', 
	    name: 'LoungeAccessDetail',
		layout: DefaultLayout,
	    component: LoungeAccessDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/meals',
		name: 'Meals',
		layout: DefaultLayout,
		component: Meals,
	},
	{
	    path: '/meal/:mealId', 
	    name: 'MealDetail',
		layout: DefaultLayout,
	    component: MealDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/seatAssignments',
		name: 'SeatAssignments',
		layout: DefaultLayout,
		component: SeatAssignments,
	},
	{
	    path: '/seatAssignment/:seatAssignmentId', 
	    name: 'SeatAssignmentDetail',
		layout: DefaultLayout,
	    component: SeatAssignmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/travelHistorys',
		name: 'TravelHistorys',
		layout: DefaultLayout,
		component: TravelHistorys,
	},
	{
	    path: '/travelHistory/:travelHistoryId', 
	    name: 'TravelHistoryDetail',
		layout: DefaultLayout,
	    component: TravelHistoryDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/connectionFlights',
		name: 'ConnectionFlights',
		layout: DefaultLayout,
		component: ConnectionFlights,
	},
	{
	    path: '/connectionFlight/:connectionFlightId', 
	    name: 'ConnectionFlightDetail',
		layout: DefaultLayout,
	    component: ConnectionFlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/delays',
		name: 'Delays',
		layout: DefaultLayout,
		component: Delays,
	},
	{
	    path: '/delay/:delayId', 
	    name: 'DelayDetail',
		layout: DefaultLayout,
	    component: DelayDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cancellations',
		name: 'Cancellations',
		layout: DefaultLayout,
		component: Cancellations,
	},
	{
	    path: '/cancellation/:cancellationId', 
	    name: 'CancellationDetail',
		layout: DefaultLayout,
	    component: CancellationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/refunds',
		name: 'Refunds',
		layout: DefaultLayout,
		component: Refunds,
	},
	{
	    path: '/refund/:refundId', 
	    name: 'RefundDetail',
		layout: DefaultLayout,
	    component: RefundDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
