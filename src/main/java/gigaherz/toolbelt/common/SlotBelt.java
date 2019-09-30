package gigaherz.toolbelt.common;

import gigaherz.toolbelt.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class SlotBelt extends Slot
{
    public SlotBelt(IInventory playerInventory, int blockedSlot, int index, int xPosition, int yPosition)
    {
        super(new IInventory(){

            final IInventory sourceInventory = playerInventory;
            final int slot = blockedSlot;
            final int subSlot = index;

            ItemStack beltStack = null;
            IItemHandlerModifiable inventory = null;

            IItemHandlerModifiable findStack()
            {
                ItemStack stack = sourceInventory.getStackInSlot(slot);
                if (stack != beltStack)
                {
                    beltStack = stack;
                    inventory = (IItemHandlerModifiable)stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
                }
                return inventory;
            }

            @Override
            public String getName()
            {
                return "toolbelt.slot.wrapper";
            }

            @Override
            public boolean hasCustomName()
            {
                return false;
            }

            @Override
            public ITextComponent getDisplayName()
            {
                return null;
            }

            @Override
            public int getSizeInventory()
            {
                return 1;
            }

            @Override
            public boolean isEmpty()
            {
                return getStackInSlot(0).getCount() <= 0;
            }

            @Override
            public ItemStack getStackInSlot(int n)
            {
                return findStack().getStackInSlot(subSlot);
            }

            @Override
            public ItemStack decrStackSize(int n, int count)
            {
                return findStack().extractItem(subSlot, count, false);
            }

            @Override
            public ItemStack removeStackFromSlot(int n)
            {
                return null;
            }

            @Override
            public void setInventorySlotContents(int n, ItemStack stack)
            {
                findStack().setStackInSlot(subSlot, stack);
            }

            @Override
            public int getInventoryStackLimit()
            {
                return findStack().getSlotLimit(subSlot);
            }

            @Override
            public void markDirty()
            {

            }

            @Override
            public boolean isUsableByPlayer(EntityPlayer player)
            {
                return false;
            }

            @Override
            public void openInventory(EntityPlayer player)
            {

            }

            @Override
            public void closeInventory(EntityPlayer player)
            {

            }

            @Override
            public boolean isItemValidForSlot(int index, ItemStack stack)
            {
                return Config.isItemStackAllowed(stack);
            }

            @Override
            public int getField(int id)
            {
                return 0;
            }

            @Override
            public void setField(int id, int value)
            {

            }

            @Override
            public int getFieldCount()
            {
                return 0;
            }

            @Override
            public void clear()
            {

            }
        }, blockedSlot, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return Config.isItemStackAllowed(stack);
    }

}