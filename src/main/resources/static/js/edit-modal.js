document.addEventListener("DOMContentLoaded", function () {
    var modal = document.getElementById("myModal");
    var span = document.getElementsByClassName("close")[0];

    // Event listener for "Open Modal" buttons
    document.querySelectorAll(".open-modal-btn").forEach(function (button) {
        button.addEventListener("click", function () {
            openModal(button);
        });
    });

    // Event listener to close the modal
    span.addEventListener("click", closeModal);
    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            closeModal();
        }
    });

    function openModal(button) {
        var reviewId = button.getAttribute("data-review-id");
        var reviewBrewery = button.getAttribute("data-review-brewery");
        var reviewDescription = button.getAttribute("data-review-description");

        // Set the modal fields based on the post details
        document.getElementById("editedBrewery").value = reviewBrewery;
        document.getElementById("editedDescription").value = reviewDescription;

        // Show the modal
        modal.style.display = "block";
    }

    function closeModal() {
        modal.style.display = "none";
    }

    // Form submission logic (you can modify this based on your needs)
    var editPostForm = document.getElementById("editPostForm");
    editPostForm.addEventListener("submit", function (event) {
        event.preventDefault();
        // Add logic to save the changes and close the modal
        closeModal();
    });
});
// document.addEventListener("DOMContentLoaded", function () {
//     var editPostModal = document.getElementById("editPostModal");
//     var editButtons = document.querySelectorAll(".edit-btn");
//     var closeButton = document.querySelector(".close");
//     var saveChangesButton = document.getElementById("saveChangesBtn");
//
//     // Function to open the modal
//     function openModal() {
//         // Show the modal
//         editPostModal.style.display = "block";
//     }
//
//     // Function to close the modal
//     function closeModal() {
//         editPostModal.style.display = "none";
//     }
//
//     // Event listeners for opening and closing the modal
//     editButtons.forEach(function (button) {
//         button.addEventListener("click", openModal);
//     });
//
//     closeButton.addEventListener("click", closeModal);
//
//     window.addEventListener("click", function (event) {
//         if (event.target === editPostModal) {
//             closeModal();
//         }
//     });
//
//     // Additional logic when "Save Changes" button is clicked
//     saveChangesButton.addEventListener("click", function () {
//         // Add logic to save changes and close the modal
//         closeModal();
//     });
// });
