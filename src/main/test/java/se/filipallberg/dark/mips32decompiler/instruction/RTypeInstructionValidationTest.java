package se.filipallberg.dark.mips32decompiler.instruction;

import org.junit.Test;
import se.filipallberg.dark.mips32decompiler.instruction.type.RTypeInstruction.RTypeInstruction;

import static junit.framework.TestCase.assertTrue;

public class RTypeInstructionValidationTest {
    @Test (expected = PartiallyLegalInstructionException.class)
    public void addShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.ADD;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void adduShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.ADDU;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void andShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.AND;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void cloShouldNotValidateIfRtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.CLO;
        instruction.rt = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void cloShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.CLO;
        instruction.rt = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void clzShouldNotValidateIfRtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.CLZ;
        instruction.rt = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void clzShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.CLZ;
        instruction.rt = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void divShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.DIV;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void divShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.DIV;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void divuShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.DIVU;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void divuShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.DIVU;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void multShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MULT;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void multShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MULT;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void multuShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MULTU;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void multuShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MULTU;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void mulShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MUL;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void maddShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MADD;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void maddShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MADD;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void madduShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MADDU;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void madduShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MADDU;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void msubShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MSUB;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void msubShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MSUB;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void msubuShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MSUBU;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void msubuShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MSUBU;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void norShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.NOR;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void orShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.OR;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void sllvShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.SLLV;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void sravShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.SRAV;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void srlvShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.SRLV;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void subShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.SUB;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void subuShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.SUBU;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void xorShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.XOR;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void teqShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TEQ;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void teqShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TEQ;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void tgeShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TGE;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void tgeShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TGE;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void tgeuShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TGEU;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void tgeuShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TGEU;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void tltShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TLT;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void tltShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TLT;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void tltuShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TLTU;
        instruction.rd = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void tltuShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.TLTU;
        instruction.rd = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfhiShouldNotValidateIfRsIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFHI;
        instruction.rs = 1;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfhiShouldNotValidateIfRtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFHI;
        instruction.rs = 0x00;
        instruction.rt = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfhiShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFHI;
        instruction.rs = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfloShouldNotValidateIfRsIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFLO;
        instruction.rs = 1;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfloShouldNotValidateIfRtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFLO;
        instruction.rs = 0x00;
        instruction.rt = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfloShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFLO;
        instruction.rs = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void mthiShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTHI;
        instruction.rd = 1;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mthiShouldNotValidateIfRtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTHI;
        instruction.rd = 0x00;
        instruction.rt = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mthiShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTHI;
        instruction.rd = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void mtloShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTLO;
        instruction.rd = 1;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mtloShouldNotValidateIfRtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTLO;
        instruction.rd = 0x00;
        instruction.rt = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mtloShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTLO;
        instruction.rd = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfc0ShouldNotValidateIfRsIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFC0;
        instruction.funct = 0x00;
        instruction.rs = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfc0ShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFC0;
        instruction.funct = 0x00;
        instruction.rs = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfc1ShouldNotValidateIfRsIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFC1;
        instruction.funct = 0x00;
        instruction.rs = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mfc1ShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFC1;
        instruction.funct = 0x00;
        instruction.rs = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mtc0ShouldNotValidateIfRsIsNot0x04() {
        RTypeInstruction instruction = RTypeInstruction.MTC0;
        instruction.funct = 0x00;
        instruction.rs = 5;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mtc0ShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTC0;
        instruction.funct = 0x00;
        instruction.rs = 0x04;
        instruction.shamt = 1;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mtc1ShouldNotValidateIfRsIsNot0x04() {
        RTypeInstruction instruction = RTypeInstruction.MTC1;
        instruction.funct = 0x00;
        instruction.rs = 5;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void mtc1ShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTC1;
        instruction.funct = 0x00;
        instruction.rs = 0x04;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void jalrShouldNotValidateIfRsIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.JALR;
        instruction.rs = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void jalrShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.JALR;
        instruction.rs = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test (expected = PartiallyLegalInstructionException.class)
    public void jrShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.JR;
        instruction.rd = 1;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void jrShouldNotValidateIfRtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.JR;
        instruction.rd = 0x00;
        instruction.rt = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void jrShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.JR;
        instruction.rd = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void nopShouldNotValidateIfRdIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.NOP;
        instruction.funct = 0x00;
        instruction.rd = 1;
        instruction.rs = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void nopShouldNotValidateIfRsIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.NOP;
        instruction.funct = 0x00;
        instruction.rd = 0x00;
        instruction.rs = 1;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void nopShouldNotValidateIfRtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.NOP;
        instruction.funct = 0x00;
        instruction.rd = 0x00;
        instruction.rs = 0x00;
        instruction.rt = 1;
        instruction.shamt = 0x00;
        instruction.validate();
    }
    
    @Test (expected = PartiallyLegalInstructionException.class)
    public void nopShouldNotValidateIfShamtIsNot0x00() {
        RTypeInstruction instruction = RTypeInstruction.NOP;
        instruction.funct = 0x00;
        instruction.rd = 0x00;
        instruction.rs = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 1;
        instruction.validate();
    }

    @Test
    public void addIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.ADD;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void adduIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.ADDU;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void andIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.AND;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void cloIsValidIfRtIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.CLO;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void clzIsValidIfRtIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.CLZ;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void divIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.DIV;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void divuIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.DIVU;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void multIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MULT;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void multuIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MULTU;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mulIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MUL;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void maddIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MADD;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void madduIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MADDU;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void msubIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MSUB;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void msubuIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MSUBU;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void norIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.NOR;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void orIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.OR;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void sllvIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.SLLV;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void sravIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.SRAV;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void srlvIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.SRLV;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void subIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.SUB;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void subuIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.SUBU;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void xorIsValidIfShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.XOR;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void teqIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.TEQ;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void tgeIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.TGE;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void tgeuIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.TGEU;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void tltIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.TLT;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void tltuIsValidIfRdIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.TLTU;
        instruction.rd = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mfhiIsValidIfRsIs0x00AndRtIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFHI;
        instruction.rs = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mfloIsValidIfRsIs0x00AndRtIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFLO;
        instruction.rs = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mthiIsValidIfRdIs0x00AndRtIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTHI;
        instruction.rd = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mtloIsValidIfRdIs0x00AndRtIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTLO;
        instruction.rd = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mfc0IsValidIfFunctIs0x00AndRsIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFC0;
        instruction.funct = 0x00;
        instruction.rs = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mfc1IsValidIfFunctIs0x00AndRsIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MFC1;
        instruction.funct = 0x00;
        instruction.rs = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mtc0IsValidIfFunctIs0x00AndRsIs0x04AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTC0;
        instruction.funct = 0x00;
        instruction.rs = 0x04;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void mtc1IsValidIfFunctIs0x00AndRsIs0x04AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.MTC1;
        instruction.funct = 0x00;
        instruction.rs = 0x04;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void jalrIsValidIfRsIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.JALR;
        instruction.rs = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void jrIsValidIfRdIs0x00AndRtIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.JR;
        instruction.rd = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

    @Test
    public void nopIsValidIfFunctIs0x00AndRdIs0x00AndRsIs0x00AndRtIs0x00AndShamtIs0x00() {
        RTypeInstruction instruction = RTypeInstruction.NOP;
        instruction.funct = 0x00;
        instruction.rd = 0x00;
        instruction.rs = 0x00;
        instruction.rt = 0x00;
        instruction.shamt = 0x00;
        assertTrue(instruction.validate());
    }

}