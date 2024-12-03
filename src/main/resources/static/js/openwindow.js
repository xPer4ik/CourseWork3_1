function openBookingModal(vehicleId) {
    document.getElementById('vehicleId').value = vehicleId;
    document.getElementById('bookingModal').style.display = 'block';
}


function closeBookingModal() {
    document.getElementById('bookingModal').style.display = 'none';
}