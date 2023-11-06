package com.example.electric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.electric.model.Appointment;
import com.example.electric.service.*;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/QrCode")
public class QRCodeController {

	@Autowired
    private AppointmentService appointmentService;
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QR_images/testing1.png";

	/**
	 * Generate QR Code
	 *
	 * This endpoint generates a QR code image based on the provided width and height dimensions.
	 *
	 * @param width The width of the QR code image.
	 * @param height The height of the QR code image.
	 * @throws Exception If there are any issues during QR code generation.
	 */


    @GetMapping("/generateQRCodeNow/{width}/{height}")
    @Operation(summary = "QR code generator", description = "Generate QR code image based on the provided width and height dimensions.", tags = {"QrCode"})
		public void download(
				// @PathVariable("codeText") String codeText,
				@PathVariable("width") Integer width,
				@PathVariable("height") Integer height)
			    throws Exception {
			        QRCodeGenerator.generateQRCodeImage("www.google.com", width, height, QR_CODE_IMAGE_PATH);
			    }


	/**
	 * Generate QR Code
	 *
	 * This endpoint generates a QR code image based on the provided code text, width, and height dimensions.
	 *
	 * @param codeText The text or data to encode in the QR code.
	 * @param width The width of the QR code image.
	 * @param height The height of the QR code image.
	 * @return A ResponseEntity containing the QR code image data.
	 * @throws Exception If there are any issues during QR code generation.
	 */
    @GetMapping("/genrateQRCode/{codeText}/{width}/{height}")
    @Operation(summary = "QR code generator", description = "Generate QR code image based on the provided code text, width, and height dimensions.", tags = {"QrCode"})
   	public ResponseEntity<byte[]> generateQRCode(
   			@PathVariable("codeText") String codeText,
   			@PathVariable("width") Integer width,
   			@PathVariable("height") Integer height)
   		    throws Exception {
   		        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
   		    }


	@GetMapping("/checkComingAppt/charger1/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment1(@PathVariable("userID") long userId){
        long chargerId = 1;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }


    @GetMapping("/checkComingAppt/charger2/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment2(@PathVariable("userID") long userId){
        long chargerId = 2;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger3/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment3(@PathVariable("userID") long userId){
        long chargerId = 3;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger4/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment4(@PathVariable("userID") long userId){
        long chargerId = 4;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger5/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment5(@PathVariable("userID") long userId){
        long chargerId = 5;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger6/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment6(@PathVariable("userID") long userId){
        long chargerId = 6;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger7/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment7(@PathVariable("userID") long userId){
        long chargerId = 7;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger8/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment8(@PathVariable("userID") long userId){
        long chargerId = 8;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger9/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment9(@PathVariable("userID") long userId){
        long chargerId = 9;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger10/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment10(@PathVariable("userID") long userId){
        long chargerId = 10;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger11/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment11(@PathVariable("userID") long userId){
        long chargerId = 11;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger12/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment12(@PathVariable("userID") long userId){
        long chargerId = 12;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger13/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment13(@PathVariable("userID") long userId){
        long chargerId = 13;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger14/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment14(@PathVariable("userID") long userId){
        long chargerId = 14;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger15/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment15(@PathVariable("userID") long userId){
        long chargerId = 15;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger16/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment16(@PathVariable("userID") long userId){
        long chargerId = 16;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger17/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment17(@PathVariable("userID") long userId){
        long chargerId = 17;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger18/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment18(@PathVariable("userID") long userId){
        long chargerId = 18;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger19/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment19(@PathVariable("userID") long userId){
        long chargerId = 19;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger20/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment20(@PathVariable("userID") long userId){
        long chargerId = 20;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger21/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment21(@PathVariable("userID") long userId){
        long chargerId = 21;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger22/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment22(@PathVariable("userID") long userId){
        long chargerId = 22;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger23/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment23(@PathVariable("userID") long userId){
        long chargerId = 23;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger24/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment24(@PathVariable("userID") long userId){
        long chargerId = 24;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger25/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment25(@PathVariable("userID") long userId){
        long chargerId = 25;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger26/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment26(@PathVariable("userID") long userId){
        long chargerId = 26;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger27/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment27(@PathVariable("userID") long userId){
        long chargerId = 27;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger28/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment28(@PathVariable("userID") long userId){
        long chargerId = 28;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger29/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment29(@PathVariable("userID") long userId){
        long chargerId = 29;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger30/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment30(@PathVariable("userID") long userId){
        long chargerId = 30;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger31/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment31(@PathVariable("userID") long userId){
        long chargerId = 31;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger32/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment32(@PathVariable("userID") long userId){
        long chargerId = 32;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger33/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment33(@PathVariable("userID") long userId){
        long chargerId = 33;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger34/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment34(@PathVariable("userID") long userId){
        long chargerId = 34;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger35/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment35(@PathVariable("userID") long userId){
        long chargerId = 35;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger36/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment36(@PathVariable("userID") long userId){
        long chargerId = 36;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger37/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment37(@PathVariable("userID") long userId){
        long chargerId = 37;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger38/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment38(@PathVariable("userID") long userId){
        long chargerId = 38;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger39/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment39(@PathVariable("userID") long userId){
        long chargerId = 39;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger40/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment40(@PathVariable("userID") long userId){
        long chargerId = 40;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger41/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment41(@PathVariable("userID") long userId){
        long chargerId = 41;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger42/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment42(@PathVariable("userID") long userId){
        long chargerId = 42;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger43/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment43(@PathVariable("userID") long userId){
        long chargerId = 43;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger44/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment44(@PathVariable("userID") long userId){
        long chargerId = 44;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger45/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment45(@PathVariable("userID") long userId){
        long chargerId = 45;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger46/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment46(@PathVariable("userID") long userId){
        long chargerId = 46;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger47/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment47(@PathVariable("userID") long userId){
        long chargerId = 47;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger48/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment48(@PathVariable("userID") long userId){
        long chargerId = 48;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger49/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment49(@PathVariable("userID") long userId){
        long chargerId = 49;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger50/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment50(@PathVariable("userID") long userId){
        long chargerId = 50;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger51/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment51(@PathVariable("userID") long userId){
        long chargerId = 51;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger52/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment52(@PathVariable("userID") long userId){
        long chargerId = 52;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger53/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment53(@PathVariable("userID") long userId){
        long chargerId = 53;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger54/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment54(@PathVariable("userID") long userId){
        long chargerId = 54;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger55/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment55(@PathVariable("userID") long userId){
        long chargerId = 55;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger56/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment56(@PathVariable("userID") long userId){
        long chargerId = 56;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger57/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment57(@PathVariable("userID") long userId){
        long chargerId = 57;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger58/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment58(@PathVariable("userID") long userId){
        long chargerId = 58;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger59/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment59(@PathVariable("userID") long userId){
        long chargerId = 59;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger60/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment60(@PathVariable("userID") long userId){
        long chargerId = 60;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger61/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment61(@PathVariable("userID") long userId){
        long chargerId = 61;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger62/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment62(@PathVariable("userID") long userId){
        long chargerId = 62;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger63/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment63(@PathVariable("userID") long userId){
        long chargerId = 63;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger64/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment64(@PathVariable("userID") long userId){
        long chargerId = 64;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger65/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment65(@PathVariable("userID") long userId){
        long chargerId = 65;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger66/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment66(@PathVariable("userID") long userId){
        long chargerId = 66;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger67/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment67(@PathVariable("userID") long userId){
        long chargerId = 67;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger68/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment68(@PathVariable("userID") long userId){
        long chargerId = 68;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger69/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment69(@PathVariable("userID") long userId){
        long chargerId = 69;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger70/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment70(@PathVariable("userID") long userId){
        long chargerId = 70;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger71/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment71(@PathVariable("userID") long userId){
        long chargerId = 71;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger72/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment72(@PathVariable("userID") long userId){
        long chargerId = 72;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger73/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment73(@PathVariable("userID") long userId){
        long chargerId = 73;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger74/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment74(@PathVariable("userID") long userId){
        long chargerId = 74;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger75/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment75(@PathVariable("userID") long userId){
        long chargerId = 75;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger76/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment76(@PathVariable("userID") long userId){
        long chargerId = 76;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger77/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment77(@PathVariable("userID") long userId){
        long chargerId = 77;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger78/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment78(@PathVariable("userID") long userId){
        long chargerId = 78;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger79/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment79(@PathVariable("userID") long userId){
        long chargerId = 79;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger80/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment80(@PathVariable("userID") long userId){
        long chargerId = 80;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger81/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment81(@PathVariable("userID") long userId){
        long chargerId = 81;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger82/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment82(@PathVariable("userID") long userId){
        long chargerId = 82;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger83/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment83(@PathVariable("userID") long userId){
        long chargerId = 83;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger84/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment84(@PathVariable("userID") long userId){
        long chargerId = 84;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger85/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment85(@PathVariable("userID") long userId){
        long chargerId = 85;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger86/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment86(@PathVariable("userID") long userId){
        long chargerId = 86;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger87/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment87(@PathVariable("userID") long userId){
        long chargerId = 87;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger88/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment88(@PathVariable("userID") long userId){
        long chargerId = 88;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger89/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment89(@PathVariable("userID") long userId){
        long chargerId = 89;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger90/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment90(@PathVariable("userID") long userId){
        long chargerId = 90;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger91/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment91(@PathVariable("userID") long userId){
        long chargerId = 91;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger92/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment92(@PathVariable("userID") long userId){
        long chargerId = 92;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger93/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment93(@PathVariable("userID") long userId){
        long chargerId = 93;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger94/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment94(@PathVariable("userID") long userId){
        long chargerId = 94;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger95/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment95(@PathVariable("userID") long userId){
        long chargerId = 95;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger96/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment96(@PathVariable("userID") long userId){
        long chargerId = 96;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger97/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment97(@PathVariable("userID") long userId){
        long chargerId = 97;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger98/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment98(@PathVariable("userID") long userId){
        long chargerId = 98;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger99/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment99(@PathVariable("userID") long userId){
        long chargerId = 99;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger100/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment100(@PathVariable("userID") long userId){
        long chargerId = 100;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger101/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment101(@PathVariable("userID") long userId){
        long chargerId = 101;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger102/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment102(@PathVariable("userID") long userId){
        long chargerId = 102;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger103/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment103(@PathVariable("userID") long userId){
        long chargerId = 103;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger104/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment104(@PathVariable("userID") long userId){
        long chargerId = 104;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger105/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment105(@PathVariable("userID") long userId){
        long chargerId = 105;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger106/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment106(@PathVariable("userID") long userId){
        long chargerId = 106;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger107/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment107(@PathVariable("userID") long userId){
        long chargerId = 107;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger108/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment108(@PathVariable("userID") long userId){
        long chargerId = 108;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger109/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment109(@PathVariable("userID") long userId){
        long chargerId = 109;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger110/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment110(@PathVariable("userID") long userId){
        long chargerId = 110;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger111/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment111(@PathVariable("userID") long userId){
        long chargerId = 111;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger112/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment112(@PathVariable("userID") long userId){
        long chargerId = 112;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger113/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment113(@PathVariable("userID") long userId){
        long chargerId = 113;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger114/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment114(@PathVariable("userID") long userId){
        long chargerId = 114;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger115/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment115(@PathVariable("userID") long userId){
        long chargerId = 115;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger116/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment116(@PathVariable("userID") long userId){
        long chargerId = 116;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger117/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment117(@PathVariable("userID") long userId){
        long chargerId = 117;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger118/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment118(@PathVariable("userID") long userId){
        long chargerId = 118;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger119/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment119(@PathVariable("userID") long userId){
        long chargerId = 119;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
    

    @GetMapping("/checkComingAppt/charger120/{userID}")
    @Operation(summary = "QR code Appointment checker", description = "Verfied user has appointment with charger, else if no appt in upcoming 20 mins, allow user to create appointment, else return cannotBookAppoinment", tags = {"QrCode"})
    public Appointment checkUpcomingAppointment120(@PathVariable("userID") long userId){
        long chargerId = 120;
        return appointmentService.checkUpcomingAppointment(chargerId, userId);
    }
}
    