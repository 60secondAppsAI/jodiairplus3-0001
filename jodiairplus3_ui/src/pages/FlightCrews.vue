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
            <flightCrew-table
            v-if="flightCrews && flightCrews.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:flightCrews="flightCrews"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-flight-crews="getAllFlightCrews"
             >

            </flightCrew-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import FlightCrewTable from "@/components/FlightCrewTable";
import FlightCrewService from "../services/FlightCrewService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FlightCrewTable,
  },
  data() {
    return {
      flightCrews: [],
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
    async getAllFlightCrews(sortBy='flightCrewId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FlightCrewService.getAllFlightCrews(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.flightCrews.length) {
					this.flightCrews = response.data.flightCrews;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching flightCrews:", error);
        }
        
      } catch (error) {
        console.error("Error fetching flightCrew details:", error);
      }
    },
  },
  mounted() {
    this.getAllFlightCrews();
  },
  created() {
    this.$root.$on('searchQueryForFlightCrewsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFlightCrews();
    })
  }
};
</script>
<style></style>
