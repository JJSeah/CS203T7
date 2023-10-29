for i in range(2,121):
    java_code = '''
    @GetMapping("/checkComingAppt/charger%d/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment%d(@PathVariable("userID") long userId){
        long chargerId = %d;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    '''

    formmated_Code = java_code % (i,i,i)
    # print(formmated_Code)

    # Appending the Java code to an QRCodeController.java
    with open("QRCodeController.java", "a") as file:
        file.write("\n" + formmated_Code)