import { createRouter, createWebHistory } from "vue-router";
import PortalView from "@/views/PortalView.vue";
import HomeView from "@/views/HomeView.vue";
import SchoolView from "@/views/SchoolView.vue";
import MajorView from "@/views/MajorView.vue";
import RecommendView from "@/views/RecommendView.vue";
import HollandView from "@/views/HollandView.vue";
import SchoolDetail from "@/views/SchoolDetail.vue";
import PythonView from "@/views/PythonView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "portal",
      component: PortalView,
    },
    {
      path: "/schoolDetail/:id",
      name: "schoolDetail",
      component: SchoolDetail,
    },
    {
      path: "/home",
      component: HomeView,
      children: [
        {
          path: "",
          redirect: "/home/school",
        },
        {
          path: "school",
          name: "school",
          component: SchoolView,
        },
        {
          path: "major",
          name: "major",
          component: MajorView,
        },
        {
          path: "recommend",
          name: "recommend",
          component: PythonView,
        },
        {
          path: "holland",
          name: "holland",
          component: HollandView,
        },

      ],
    },
  ],
});

export default router;
