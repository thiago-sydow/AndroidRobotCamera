################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../jni/DetectionBasedTracker_jni.cpp \
../jni/cvjni.cpp 

OBJS += \
./jni/DetectionBasedTracker_jni.o \
./jni/cvjni.o 

CPP_DEPS += \
./jni/DetectionBasedTracker_jni.d \
./jni/cvjni.d 


# Each subdirectory must supply rules for building sources it contributes
jni/%.o: ../jni/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


