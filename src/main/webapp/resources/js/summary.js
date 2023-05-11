const nextStepBtn = document.querySelectorAll(".next-step");

nextStepBtn.forEach(btn => {
   btn.addEventListener("click", () => {
       // content & quantity summary
       const quantity = document.querySelector("input[name=quantity]").value;
       const categories = document.querySelectorAll("input[name=categories]:checked");
       let content = "";
       categories.forEach((category, i) => {
           const description = category.parentElement.querySelector(".description");
           if (i === 0) {
               content += description.innerHTML;
           } else {
               content += ", " + description.innerHTML;
           }
       })
       const contentQuantitySummary = document.querySelector(".content-quantity");
       contentQuantitySummary.innerHTML = content + " - " + quantity + " worki";

       // institution summary
       const institutionRadio = document.querySelector("input[name=institution]:checked");
       const institutionName = institutionRadio.parentElement.querySelector(".title").innerHTML;
       const institutionSummary = document.querySelector(".institution");
       institutionSummary.innerHTML = institutionName;

       // pick up address summary
       const addressInputs = document.querySelectorAll("input[type=text]");
       const addressSummaryElements = document.querySelectorAll(".pickup-address ul li");
       for(let i = 0; i < addressInputs.length; i++) {
           if(addressSummaryElements[i]) {
               addressSummaryElements[i].innerHTML = addressInputs[i].value;
           }
       }

       // pick up time summary
       const pickUpDate = document.querySelector("input[type=date]").value;
       const pickUpTime = document.querySelector("input[type=time]").value;
       const comment = document.querySelector("textarea[name=pickUpComment]").value;
       const pickUpSummaryElements = document.querySelector(".pickup-time ul").children;
       [...pickUpSummaryElements][0].innerHTML = pickUpDate;
       [...pickUpSummaryElements][1].innerHTML = pickUpTime;
       [...pickUpSummaryElements][2].innerHTML = comment === "" ? "Brak uwag" : comment;
   });
});