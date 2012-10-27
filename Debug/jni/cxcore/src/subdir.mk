################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../jni/cxcore/src/cxalloc.cpp \
../jni/cxcore/src/cxarithm.cpp \
../jni/cxcore/src/cxarray.cpp \
../jni/cxcore/src/cxcmp.cpp \
../jni/cxcore/src/cxconvert.cpp \
../jni/cxcore/src/cxcopy.cpp \
../jni/cxcore/src/cxdatastructs.cpp \
../jni/cxcore/src/cxdrawing.cpp \
../jni/cxcore/src/cxdxt.cpp \
../jni/cxcore/src/cxerror.cpp \
../jni/cxcore/src/cximage.cpp \
../jni/cxcore/src/cxjacobieigens.cpp \
../jni/cxcore/src/cxlogic.cpp \
../jni/cxcore/src/cxlut.cpp \
../jni/cxcore/src/cxmathfuncs.cpp \
../jni/cxcore/src/cxmatmul.cpp \
../jni/cxcore/src/cxmatrix.cpp \
../jni/cxcore/src/cxmean.cpp \
../jni/cxcore/src/cxmeansdv.cpp \
../jni/cxcore/src/cxminmaxloc.cpp \
../jni/cxcore/src/cxnorm.cpp \
../jni/cxcore/src/cxouttext.cpp \
../jni/cxcore/src/cxpersistence.cpp \
../jni/cxcore/src/cxprecomp.cpp \
../jni/cxcore/src/cxrand.cpp \
../jni/cxcore/src/cxsumpixels.cpp \
../jni/cxcore/src/cxsvd.cpp \
../jni/cxcore/src/cxswitcher.cpp \
../jni/cxcore/src/cxtables.cpp \
../jni/cxcore/src/cxutils.cpp \
../jni/cxcore/src/dummy.cpp 

OBJS += \
./jni/cxcore/src/cxalloc.o \
./jni/cxcore/src/cxarithm.o \
./jni/cxcore/src/cxarray.o \
./jni/cxcore/src/cxcmp.o \
./jni/cxcore/src/cxconvert.o \
./jni/cxcore/src/cxcopy.o \
./jni/cxcore/src/cxdatastructs.o \
./jni/cxcore/src/cxdrawing.o \
./jni/cxcore/src/cxdxt.o \
./jni/cxcore/src/cxerror.o \
./jni/cxcore/src/cximage.o \
./jni/cxcore/src/cxjacobieigens.o \
./jni/cxcore/src/cxlogic.o \
./jni/cxcore/src/cxlut.o \
./jni/cxcore/src/cxmathfuncs.o \
./jni/cxcore/src/cxmatmul.o \
./jni/cxcore/src/cxmatrix.o \
./jni/cxcore/src/cxmean.o \
./jni/cxcore/src/cxmeansdv.o \
./jni/cxcore/src/cxminmaxloc.o \
./jni/cxcore/src/cxnorm.o \
./jni/cxcore/src/cxouttext.o \
./jni/cxcore/src/cxpersistence.o \
./jni/cxcore/src/cxprecomp.o \
./jni/cxcore/src/cxrand.o \
./jni/cxcore/src/cxsumpixels.o \
./jni/cxcore/src/cxsvd.o \
./jni/cxcore/src/cxswitcher.o \
./jni/cxcore/src/cxtables.o \
./jni/cxcore/src/cxutils.o \
./jni/cxcore/src/dummy.o 

CPP_DEPS += \
./jni/cxcore/src/cxalloc.d \
./jni/cxcore/src/cxarithm.d \
./jni/cxcore/src/cxarray.d \
./jni/cxcore/src/cxcmp.d \
./jni/cxcore/src/cxconvert.d \
./jni/cxcore/src/cxcopy.d \
./jni/cxcore/src/cxdatastructs.d \
./jni/cxcore/src/cxdrawing.d \
./jni/cxcore/src/cxdxt.d \
./jni/cxcore/src/cxerror.d \
./jni/cxcore/src/cximage.d \
./jni/cxcore/src/cxjacobieigens.d \
./jni/cxcore/src/cxlogic.d \
./jni/cxcore/src/cxlut.d \
./jni/cxcore/src/cxmathfuncs.d \
./jni/cxcore/src/cxmatmul.d \
./jni/cxcore/src/cxmatrix.d \
./jni/cxcore/src/cxmean.d \
./jni/cxcore/src/cxmeansdv.d \
./jni/cxcore/src/cxminmaxloc.d \
./jni/cxcore/src/cxnorm.d \
./jni/cxcore/src/cxouttext.d \
./jni/cxcore/src/cxpersistence.d \
./jni/cxcore/src/cxprecomp.d \
./jni/cxcore/src/cxrand.d \
./jni/cxcore/src/cxsumpixels.d \
./jni/cxcore/src/cxsvd.d \
./jni/cxcore/src/cxswitcher.d \
./jni/cxcore/src/cxtables.d \
./jni/cxcore/src/cxutils.d \
./jni/cxcore/src/dummy.d 


# Each subdirectory must supply rules for building sources it contributes
jni/cxcore/src/%.o: ../jni/cxcore/src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


